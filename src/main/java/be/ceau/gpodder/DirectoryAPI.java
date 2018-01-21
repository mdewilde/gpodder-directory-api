/*
	Copyright 2018 Marceau Dewilde <m@ceau.be>
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
		https://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package be.ceau.gpodder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 * Implementation of gpodder.net Directory API.
 * 
 * @see <a href=
 *      "https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html">Directory API
 *      documentation</a>
 */
public class DirectoryAPI {

	private static final Logger logger = LoggerFactory.getLogger(DirectoryAPI.class);

	private static final String SITE = "https://gpodder.net/";

	private static final ObjectReader TAG_LIST_READER = new ObjectMapper()
			.reader()
			.forType(new TypeReference<List<Tag>>() {});
	
	private static final ObjectReader PODCAST_LIST_READER = new ObjectMapper()
			.reader()
			.forType(new TypeReference<List<Podcast>>() {});

	/**
	 * Retrieve top tags
	 * 
	 * @param count
	 *            maximum number of podcasts to get
	 * @return a {@link List} of {@link Tag} instances, never {@code null}
	 * @throws IOException
	 *             as thrown during the web request
	 * @see <a href=
	 *      "https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#retrieve-top-tags">tags
	 *      call documentation</a>
	 */
	public List<Tag> getTags(int count) throws IOException {
		requireGreaterThanZero(count);
		String url = SITE + "api/2/tags/" + count + ".json";
		String response = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
		logger.trace("API request/response : {} -> {}", url, response);
		return TAG_LIST_READER.readValue(response);
	}

	/**
	 * Retrieve podcasts for tag
	 * 
	 * @param tag
	 * @param count
	 *            maximum number of podcasts to get
	 * @return a {@link List} of {@link Podcast} instances, never {@code null}
	 * @throws IOException
	 *             as thrown during the web request
	 * @see <a href=
	 *      "https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#retrieve-podcasts-for-tag">toplist
	 *      call documentation</a>
	 */
	public List<Podcast> getPodcastsForTag(String tag, int count) throws IOException {
		requireNotBlank(tag);
		requireGreaterThanZero(count);
		String url = SITE + "api/2/tag/" + tag + "/" + count + ".json";
		return getPodcasts(url);
	}

	/**
	 * Podcast toplist
	 * 
	 * @param count
	 *            maximum number of podcasts to get
	 * @return a {@link List} of {@link Podcast} instances, never {@code null}
	 * @throws IOException
	 *             as thrown during the web request
	 * @see <a href=
	 *      "https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#podcast-toplist">toplist
	 *      call documentation</a>
	 */
	public List<Podcast> getToplist(int count) throws IOException {
		requireGreaterThanZero(count);
		String url = SITE + "toplist/" + count + ".json";
		return getPodcasts(url);
	}

	/**
	 * Podcast search
	 * 
	 * @param query
	 *            {@link String} search term(s)
	 * @return a {@link List} of {@link Podcast} instances, never {@code null}
	 * @throws IOException
	 *             as thrown during the web request
	 * @see <a href=
	 *      "https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#podcast-search">search
	 *      call documentation</a>
	 */
	public List<Podcast> search(String query) throws IOException {
		requireNotBlank(query);
		String url = SITE + "search.json?q=" + query;
		return getPodcasts(url);
	}

	private List<Podcast> getPodcasts(String url) throws IOException {
		String response = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
		logger.trace("API request/response : {} -> {}", url, response);
		return PODCAST_LIST_READER.readValue(response);
	}

	private void requireGreaterThanZero(int count) {
		if (count < 1) {
			throw new IllegalArgumentException("count must be greater than 0");
		}
	}

	private void requireNotBlank(String string) {
		if (string == null || string.trim().isEmpty()) {
			throw new IllegalArgumentException("string can not be blank");
		}
	}

}
