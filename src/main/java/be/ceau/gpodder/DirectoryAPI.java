package be.ceau.gpodder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class DirectoryAPI {

	private static final String SITE = "https://gpodder.net/";
	
	private static final ObjectReader TAG_LIST_READER = new ObjectMapper().reader().forType(new TypeReference<List<Tag>>() {});
	private static final ObjectReader PODCAST_LIST_READER = new ObjectMapper().reader().forType(new TypeReference<List<Podcast>>() {});

	public List<Tag> getTags(int count) throws IOException {
		if (count < 1) {
			throw new IllegalArgumentException("count must be greater than 0");
		}
		String url = SITE + "api/2/tags/" + count + ".json";
		String response = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
		return TAG_LIST_READER.readValue(response);
	}
	
	public List<Podcast> getPodcastsForTag(String tag, int count) throws IOException {
		validateString(tag);
		validateCount(count);
		String url = SITE + "api/2/tag/" + tag + "/" + count + ".json";
		return getPodcasts(url);
	}
	
	public List<Podcast> getToplist(int count) throws IOException {
		validateCount(count);
		String url = SITE + "toplist/" + count + ".json";
		return getPodcasts(url);
	}

	public List<Podcast> search(String query) throws IOException {
		validateString(query);
		String url = SITE + "search.json?q=" + query;
		return getPodcasts(url);
	}

	private List<Podcast> getPodcasts(String url) throws IOException {
		String response = IOUtils.toString(new URL(url), StandardCharsets.UTF_8);
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
