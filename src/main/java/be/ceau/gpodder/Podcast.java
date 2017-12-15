/*
	Copyright 2017 Marceau Dewilde <m@ceau.be>
	
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A podcast, as returned by gpodder.net's directory API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Podcast {

	@JsonProperty("url")
	private String url;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("subscribers")
	private int subscribers;
	
	@JsonProperty("subscribers_last_week")
	private int subscribersLastWeek;

	@JsonProperty("logo_url")
	private String logoUrl;

	@JsonProperty("scaled_logo_url")
	private String scaledLogoUrl;

	@JsonProperty("website")
	private String website;

	@JsonProperty("mygpo_link")
	private String gpodderUrl;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}

	public int getSubscribersLastWeek() {
		return subscribersLastWeek;
	}

	public void setSubscribersLastWeek(int subscribersLastWeek) {
		this.subscribersLastWeek = subscribersLastWeek;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getScaledLogoUrl() {
		return scaledLogoUrl;
	}

	public void setScaledLogoUrl(String scaledLogoUrl) {
		this.scaledLogoUrl = scaledLogoUrl;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getGpodderUrl() {
		return gpodderUrl;
	}

	public void setGpodderUrl(String gpodderUrl) {
		this.gpodderUrl = gpodderUrl;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Podcast [url=")
				.append(url)
				.append(", title=")
				.append(title)
				.append(", description=")
				.append(description)
				.append(", subscribers=")
				.append(subscribers)
				.append(", subscribersLastWeek=")
				.append(subscribersLastWeek)
				.append(", logoUrl=")
				.append(logoUrl)
				.append(", scaledLogoUrl=")
				.append(scaledLogoUrl)
				.append(", website=")
				.append(website)
				.append(", gpodderUrl=")
				.append(gpodderUrl)
				.append("]")
				.toString();
	}

}
