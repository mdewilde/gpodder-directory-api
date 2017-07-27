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

public class DirectoryAPI {

	private static final Logger logger = LoggerFactory.getLogger(DirectoryAPI.class);
	
	private static final String SITE = "https://gpodder.net/";
	
	private static final ObjectReader TAG_LIST_READER = new ObjectMapper().reader().forType(new TypeReference<List<Tag>>() {});
	private static final ObjectReader PODCAST_LIST_READER = new ObjectMapper().reader().forType(new TypeReference<List<Podcast>>() {});

	/**
	 * 
	 * @param count
	 * @return
	 * @throws IOException
	 * @see <a href="https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#retrieve-top-tags">tags call documentation</a>
	 */
	public List<Tag> getTags(int count) throws IOException {
		if (count < 1) {
			throw new IllegalArgumentException("count must be greater than 0");
		}
		String url = SITE + "api/2/tags/" + count + ".json";
		String response = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
		logger.trace("API request/response : {} -> {}", url, response);
		return TAG_LIST_READER.readValue(response);
	}
	
	/**
	 * 
	 * @param tag
	 * @param count
	 * @return
	 * @throws IOException
	 * @see <a href="https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#retrieve-podcasts-for-tag">toplist call documentation</a>
	 */
	public List<Podcast> getPodcastsForTag(String tag, int count) throws IOException {
		validateString(tag);
		validateCount(count);
		String url = SITE + "api/2/tag/" + tag + "/" + count + ".json";
		return getPodcasts(url);
	}
	
	/**
	 * 
	 * @param count
	 * @return
	 * @throws IOException
	 * @see <a href="https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#podcast-toplist">toplist call documentation</a>
	 */
	public List<Podcast> getToplist(int count) throws IOException {
		validateCount(count);
		String url = SITE + "toplist/" + count + ".json";
		return getPodcasts(url);
	}

	/**
	 * 
	 * @param query
	 * @return
	 * @throws IOException
	 * @see <a href="https://gpoddernet.readthedocs.io/en/latest/api/reference/directory.html#podcast-search">search call documentation</a>
	 */
	public List<Podcast> search(String query) throws IOException {
		validateString(query);
		String url = SITE + "search.json?q=" + query;
		return getPodcasts(url);
	}

	private List<Podcast> getPodcasts(String url) throws IOException {
		String response = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
		logger.trace("API request/response : {} -> {}", url, response);
		return PODCAST_LIST_READER.readValue(response);
	}

	private void validateCount(int count) {
		if (count < 1) {
			throw new IllegalArgumentException("count must be greater than 0");
		}
	}
	
	private void validateString(String string) {
		if (string == null || string.trim().isEmpty()) {
			throw new IllegalArgumentException("string can not be blank");
		}
	}

}
