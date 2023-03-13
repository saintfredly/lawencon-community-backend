package com.lawencon.community.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.post.PojoPostCommentReq;
import com.lawencon.community.pojo.post.PojoPostReq;
import com.lawencon.community.pojo.post.bookmark.PojoPostBookmarkReq;
import com.lawencon.community.pojo.post.like.PojoPostLikeReq;
import com.lawencon.community.service.PostService;

@RestController
@RequestMapping("posts")
public class PostController {

	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<PojoRes> save(@RequestBody PojoPostReq data) {
		final PojoRes res = postService.save(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PojoRes> update(@RequestBody PojoPostReq data) {
		final PojoRes res = postService.update(data);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PojoRes> deleteById(@PathVariable("id") String id) {
		final PojoRes res = postService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<PojoRes> saveComment(@RequestBody PojoPostCommentReq data) {
		final PojoRes res = postService.saveComment(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PostMapping("/like")
	public ResponseEntity<PojoRes> saveLike(@RequestBody PojoPostLikeReq data) {
		final PojoRes res = postService.saveLike(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/like/{id}")
	public ResponseEntity<PojoRes> deleteLike(@PathVariable("id") String id) {
		final PojoRes res = postService.deleteLike(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/bookmark")
	public ResponseEntity<PojoRes> saveBookmark(@RequestBody PojoPostBookmarkReq data) {
		final PojoRes res = postService.saveBookmark(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/bookmark/{id}")
	public ResponseEntity<PojoRes> deleteBookmark(@PathVariable("id") String id) {
		final PojoRes res = postService.deleteBookmark(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
