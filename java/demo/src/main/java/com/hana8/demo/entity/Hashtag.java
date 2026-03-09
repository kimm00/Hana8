package com.hana8.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@ManyToMany
public class Hashtag {
	@Column(columnDefinition = "int unsigned")
	private Long id;

	@Column(nullable = false, length = 30)
	private String tag;

	@ManyToMany
	@JoinTable(name = "HashtagPost",
			joinColumns={@JoinColumn(name = "hashtag")},
			inverseJoinColumns = {@JoinColumn(name = "post")}
	)

	@Builder
	private List<Post> hashtagPosts = new ArrayList<>();




}
