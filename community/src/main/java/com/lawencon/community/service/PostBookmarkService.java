package com.lawencon.community.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.PostBookmarkDao;
import com.lawencon.community.dao.PostDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Post;
import com.lawencon.community.model.PostBookmark;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.post.bookmark.PojoPostBookmarkReq;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PostBookmarkService {
	private final PostBookmarkDao postBookmarkDao;
	private final PostDao postDao;
	private final UserDao userDao;
	
	@Inject
	private final PrincipalService principalService;
	
	public PostBookmarkService(PostDao postDao,UserDao userDao,PostBookmarkDao postBookmarkDao,PrincipalService principalService) {
		this.postBookmarkDao = postBookmarkDao;
		this.principalService = principalService;
		this.postDao = postDao;
		this.userDao = userDao;
	}
	
	public PojoRes save(PojoPostBookmarkReq data) {
		ConnHandler.begin();
		final PostBookmark postBookmark = new PostBookmark();

		final Post post = postDao.getByIdRef(Post.class, data.getPostId());
		postBookmark.setPost(post);

		final User user = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		postBookmark.setUser(user);

		postBookmark.setStatus(data.getStatus());
		postBookmark.setIsActive(true);

		postBookmarkDao.save(postBookmark);

		ConnHandler.commit();
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Bookmark posts!");
		return pojoRes;
	}

	public PojoRes deleteById(String id) {
		ConnHandler.begin();
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Unbookmark Posts!");
		final PojoRes pojoResFail = new PojoRes();
		pojoResFail.setMessage("Unbookmark Failed!");

		Boolean result = postBookmarkDao.deleteById(PostBookmark.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}
	}
}
