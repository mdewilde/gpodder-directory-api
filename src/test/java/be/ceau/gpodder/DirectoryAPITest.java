package be.ceau.gpodder;

import java.io.IOException;

import org.junit.Test;

import be.ceau.gpodder.DirectoryAPI;

public class DirectoryAPITest {

	private final DirectoryAPI gPodder = new DirectoryAPI();
	
	@Test
	public void tag() throws IOException {
		System.out.println("tag()");
		gPodder.getTags(5).forEach(System.out::println);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tagInvalidCount() throws IOException {
		gPodder.getTags(-1).forEach(System.out::println);
	}

	@Test
	public void podcastsForTag() throws IOException {
		System.out.println("podcastsForTag()");
		gPodder.getPodcastsForTag("blog-talk-radio", 3).forEach(System.out::println);
	}

	@Test
	public void toplist() throws IOException {
		System.out.println("toplist()");
		gPodder.getToplist(5).forEach(System.out::println);
	}

	@Test
	public void search() throws IOException {
		System.out.println("search()");
		gPodder.search("test").forEach(System.out::println);
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchBlankArgumentException() throws IOException {
		System.out.println("searchBlankArgumentException()");
		gPodder.search("    ").forEach(System.out::println);
	}

}
