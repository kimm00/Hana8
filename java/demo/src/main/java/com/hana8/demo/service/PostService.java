package com.hana8.demo.service;

import com.hana8.demo.dto.PostDTO;
import com.hana8.demo.dto.PostListDTO;
import com.hana8.demo.dto.ReplyDTO;
import com.hana8.demo.entity.Post;
import com.hana8.demo.entity.QPost;
import com.hana8.demo.entity.Reply;
import com.hana8.demo.mapper.PostMapper;
import com.hana8.demo.mapper.ReplyMapper;
import com.hana8.demo.repository.MemberRepository;
import com.hana8.demo.repository.PostRepository;
import com.hana8.demo.repository.ReplyRepository;
import com.querydsl.core.BooleanBuilder;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository repository;
	private final ReplyRepository replyRepository;
	private final MemberRepository memberRepository;

	private final PostMapper postMapper;
	private final ReplyMapper replyMapper;

	public List<PostDTO> getPosts(PostListDTO dto) {
		System.out.println("dto = " + dto);
		PageRequest pager = PageRequest.of(dto.getPage() - 1, dto.getPageSize(),
				Sort.by("id").descending());

		QPost post = QPost.post;
		BooleanBuilder bb = new BooleanBuilder();
		if (StringUtils.hasText(dto.getTitle())) {
			bb.and(post.title.contains(dto.getTitle()));
		}

		if (StringUtils.hasText(dto.getBody())) {
			bb.and(post.body.body.contains(dto.getBody()));
		}

		if (StringUtils.hasText(dto.getWriter())) {
			bb.and(post.writer.nickname.contains(dto.getWriter()));
		}

		if (StringUtils.hasText(dto.getWritedate())) {
			ZoneId zone = ZoneId.of("Asia/Seoul");
			// LocalDateTime start = dto.parseWritedate().atStartOfDay();
			ZonedDateTime start = dto.parseWritedate().atStartOfDay(zone);
			// LocalDateTime end = dto.parseWritedate().atTime(LocalTime.MAX);
			// LocalDateTime end = dto.parseWritedate().plusDays(1).atStartOfDay();
			ZonedDateTime end = dto.parseWritedate().plusDays(1).atStartOfDay(zone);
			System.out.println("start, end = " + start + ',' + end);
			// bb.and(post.createdAt.between(start, end));
			bb.and(post.createdAt.goe(start.toLocalDateTime())
					.and(post.createdAt.lt(end.toLocalDateTime())));
		}

		List<Post> posts = repository.findAll(bb, pager).getContent();

		return posts.stream().map(postMapper::toDTO).toList();
	}

	public PostDTO getPost(Long id) {
		Post post = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Post #%d is not found!".formatted(id)));
		PostDTO dto = postMapper.toDTO(post);
		dto.setReplies(replyMapper.toDTOList(replyRepository.findAllByPostId(id)));

		return dto;
	}

	public PostDTO registPost(PostDTO dto) {
		// 1. 매퍼를 통해 엔티티 기본 변환
		Post post = postMapper.toEntity(dto);

		// 2. 작성자(Member) 연관관계 설정
		if (dto.getWriter() != null && dto.getWriter().getId() != null) {
			post.setWriter(memberRepository.getReferenceById(dto.getWriter().getId()));
		}

		// 3. PostBody 설정 (기존 로직 유지)
		if (dto.getBody() != null) {
			post.setBody(postMapper.toEntity(dto.getBody()));
		}

		return postMapper.toDTO(repository.save(post));
	}

	public PostDTO editPost(PostDTO dto) {
		Post oldPost = repository.findById(dto.getId())
				.orElseThrow(
						() -> new IllegalArgumentException("Post #%d is not found!".formatted(dto.getId())));

		// 필드 업데이트
		oldPost.setTitle(dto.getTitle());

		if (dto.getBody() != null) {
			oldPost.setBody(postMapper.toEntity(dto.getBody()));
		}

		// 작성자 변경 (MemberDTO -> Member)
		if (dto.getWriter() != null && dto.getWriter().getId() != null) {
			oldPost.setWriter(memberRepository.getReferenceById(dto.getWriter().getId()));
		}

		return postMapper.toDTO(repository.save(oldPost));
	}

	public int removePost(Long id) {
		repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Post #%d is not found!".formatted(id)));

		return repository.deletePost(id);
	}

	public List<ReplyDTO> getReplies(Long postId) {
		List<Reply> replies = replyRepository.findAllByPostId(postId);
		// replies.stream().map(replyMapper::toDTO)
		return replyMapper.toDTOList(replies);
	}

	public ReplyDTO getReply(Long id) {
		return replyMapper.toDTO(
				replyRepository.findById(id)
						.orElseThrow(() -> new IllegalArgumentException("Reply not Found!")));
	}

	public ReplyDTO addReply(ReplyDTO dto) {
		Post post = repository.findById(dto.getPostId()).orElseThrow();
		Reply reply = replyMapper.toEntity(dto);
		reply.setPost(post);
		return replyMapper.toDTO(replyRepository.save(reply));
	}

	public ReplyDTO editReply(ReplyDTO dto) {
		Reply reply = replyRepository.findById(dto.getId())
				.orElseThrow(() -> new IllegalArgumentException("Reply not Found!"));

		reply.setReply(dto.getReply());
		return replyMapper.toDTO(replyRepository.save(reply));
	}

	public int removeReply(Long id) {
		replyRepository.findById(id).orElseThrow();
		return replyRepository.deleteByReplyId(id);
	}
}
