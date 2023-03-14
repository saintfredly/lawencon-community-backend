package com.lawencon.community.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.PostDao;
import com.lawencon.community.dao.PostLikeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Post;
import com.lawencon.community.model.PostLike;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.post.like.PojoPostLikeReq;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PostLikeService {
	private final PostLikeDao postLikeDao;
	private final PostDao postDao;
	private final UserDao userDao;
	
	@Inject
	private final PrincipalService principalService;

	public PostLikeService(PostLikeDao postLikeDao,PostDao postDao,UserDao userDao,PrincipalService principalService) {
		this.postLikeDao = postLikeDao;
		this.postDao = postDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}

	public PojoRes save(PojoPostLikeReq data) {
		ConnHandler.begin();
		final PostLike postLike = new PostLike();

		final Post post = postDao.getByIdRef(Post.class, data.getPostId());
		postLike.setPost(post);

		final User user = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		postLike.setUser(user);

		postLike.setStatus(data.getStatus());
		postLike.setIsActive(true);

		postLikeDao.save(postLike);

		ConnHandler.commit();
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Like posts!");
		return pojoRes;
	}

	public PojoRes deleteById(String id) {
		ConnHandler.begin();
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Unlike Posts!");
		final PojoRes pojoResFail = new PojoRes();
		pojoResFail.setMessage("Unlike Failed!");

		Boolean result = postLikeDao.deleteById(PostLike.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}
	}
}
