package com.lawencon.community.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lawencon.base.ConnHandler;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.FileDao;
import com.lawencon.community.dao.PostBookmarkDao;
import com.lawencon.community.dao.PostCommentDao;
import com.lawencon.community.dao.PostDao;
import com.lawencon.community.dao.PostLikeDao;
import com.lawencon.community.dao.PostPollingDao;
import com.lawencon.community.dao.PostPollingDetailDao;
import com.lawencon.community.dao.PostTypeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.File;
import com.lawencon.community.model.Post;
import com.lawencon.community.model.PostBookmark;
import com.lawencon.community.model.PostComment;
import com.lawencon.community.model.PostLike;
import com.lawencon.community.model.PostPolling;
import com.lawencon.community.model.PostPollingDetail;
import com.lawencon.community.model.PostType;
import com.lawencon.community.model.User;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.post.PojoPostCommentReq;
import com.lawencon.community.pojo.post.PojoPostReq;
import com.lawencon.community.pojo.post.bookmark.PojoPostBookmarkReq;
import com.lawencon.community.pojo.post.like.PojoPostLikeReq;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PostService {

	private final PostDao postDao;
	private final PostPollingDao postPollingDao;
	private final PostPollingDetailDao postPollingDetailDao;
	private final PostTypeDao postTypeDao;
	private final FileDao fileDao;
	private final CategoryDao categoryDao;
	private final UserDao userDao;
	private final PostCommentDao postCommentDao;
	private final PostLikeDao postLikeDao;
	private final PostBookmarkDao postBookmarkDao;

	@Inject
	private final PrincipalService principalService;

	public PostService(PostLikeDao postLikeDao,PostBookmarkDao postBookmarkDao,PostCommentDao postCommentDao, UserDao userDao, PrincipalService principalService,
			PostPollingDetailDao postPollingDetailDao, PostPollingDao postPollingDao, PostDao postDao,
			PostTypeDao postTypeDao, FileDao fileDao, CategoryDao categoryDao) {
		this.postDao = postDao;
		this.postTypeDao = postTypeDao;
		this.fileDao = fileDao;
		this.categoryDao = categoryDao;
		this.postPollingDao = postPollingDao;
		this.postPollingDetailDao = postPollingDetailDao;
		this.principalService = principalService;
		this.userDao = userDao;
		this.postCommentDao = postCommentDao;
		this.postBookmarkDao = postBookmarkDao;
		this.postLikeDao = postLikeDao;
	}

	public PojoRes save(PojoPostReq data) {
		ConnHandler.begin();

		final Post post = new Post();

		post.setPostTitle(data.getPostTitle());
		post.setPostContent(data.getPostContent());

		final PostType postType = postTypeDao.getByIdRef(PostType.class, data.getPostTypeId());

		post.setPostType(postType);

		final File file = new File();
		file.setFileName(data.getPhoto().getFileName());
		file.setFileContent(data.getPhoto().getFileContent());
		file.setFileExtension(data.getPhoto().getFileExtension());
		file.setIsActive(true);

		fileDao.save(file);

		post.setFile(file);

		final Category category = categoryDao.getByIdRef(Category.class, data.getCategoryId());

		post.setCategory(category);
		post.setIsActive(true);

		postDao.save(post);

		if (data.getPolling() != null) {
			final PostPolling postPolling = new PostPolling();

			postPolling.setPost(post);
			postPolling.setPollingTitle(data.getPolling().getPollingTitle());

			final File filePolling = new File();
			filePolling.setFileName(data.getPolling().getPhoto().getFileName());
			filePolling.setFileContent(data.getPolling().getPhoto().getFileContent());
			filePolling.setFileExtension(data.getPolling().getPhoto().getFileExtension());
			filePolling.setIsActive(true);

			fileDao.save(filePolling);

			postPolling.setFile(filePolling);
			postPolling.setIsActive(true);

			postPollingDao.save(postPolling);

			for (int i = 0; i < data.getPolling().getPollingDetail().size(); i++) {
				final PostPollingDetail polDetail = new PostPollingDetail();

				polDetail.setPostPolling(postPolling);
				polDetail.setOptionContent(data.getPolling().getPollingDetail().get(i).getOptionContent());

				polDetail.setIsActive(true);

				postPollingDetailDao.save(polDetail);
			}

		}

		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Successfully Posted!");
		return pojoRes;
	}

	public PojoRes update(PojoPostReq data) {
		ConnHandler.begin();

		Post post = new Post();
		post = postDao.getByIdAndDetach(data.getId()).get();

		post.setPostTitle(data.getPostTitle());
		post.setPostContent(data.getPostContent());

		final PostType postType = postTypeDao.getByIdRef(PostType.class, data.getPostTypeId());

		post.setPostType(postType);

		File file = new File();
		file = fileDao.getByIdAndDetach(File.class, data.getPhoto().getId());

		file.setFileName(data.getPhoto().getFileName());
		file.setFileContent(data.getPhoto().getFileContent());
		file.setFileExtension(data.getPhoto().getFileExtension());

		file.setVersion(data.getPhoto().getVer());
		file.setIsActive(true);

		fileDao.save(file);

		post.setFile(file);

		final Category category = categoryDao.getByIdRef(Category.class, data.getCategoryId());

		post.setCategory(category);
		post.setVersion(data.getVer());
		post.setIsActive(data.getIsActive());

		postDao.save(post);

		if (data.getPolling() != null) {
			PostPolling postPolling = new PostPolling();
			postPolling = postPollingDao.getByIdAndDetach(PostPolling.class, data.getPolling().getId());

			postPolling.setPost(post);
			postPolling.setPollingTitle(data.getPolling().getPollingTitle());

			File filePolling = new File();
			filePolling = fileDao.getByIdAndDetach(File.class, data.getPolling().getPhoto().getId());

			filePolling.setFileName(data.getPolling().getPhoto().getFileName());
			filePolling.setFileContent(data.getPolling().getPhoto().getFileContent());
			filePolling.setFileExtension(data.getPolling().getPhoto().getFileExtension());

			filePolling.setVersion(data.getPolling().getPhoto().getVer());
			filePolling.setIsActive(true);

			fileDao.save(filePolling);

			postPolling.setFile(filePolling);
			postPolling.setVersion(data.getPolling().getVer());
			postPolling.setIsActive(true);

			postPollingDao.save(postPolling);

			for (int i = 0; i < data.getPolling().getPollingDetail().size(); i++) {
				PostPollingDetail polDetail = new PostPollingDetail();
				polDetail = postPollingDetailDao.getByIdAndDetach(PostPollingDetail.class,
						data.getPolling().getPollingDetail().get(i).getId());

				polDetail.setPostPolling(postPolling);
				polDetail.setOptionContent(data.getPolling().getPollingDetail().get(i).getOptionContent());

				polDetail.setVersion(data.getPolling().getPollingDetail().get(i).getVer());
				polDetail.setIsActive(true);

				postPollingDetailDao.save(polDetail);
			}
		}

		ConnHandler.commit();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Post Updated!");
		return pojoRes;
	}

	public PojoRes deleteById(String id) {
		ConnHandler.begin();

		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Delete Success!");
		final PojoRes pojoResFail = new PojoRes();
		pojoResFail.setMessage("Delete Failed!");

		Boolean result = postDao.deleteById(Post.class, id);
		ConnHandler.commit();

		if (result == true) {
			return pojoRes;
		} else {
			return pojoResFail;
		}
	}

	public PojoRes saveComment(PojoPostCommentReq data) {
		ConnHandler.begin();
		final PostComment postComment = new PostComment();

		final Post post = postDao.getByIdRef(Post.class, data.getPostId());
		postComment.setPost(post);

		postComment.setTheComment(data.getTheComment());

		final User user = userDao.getByIdRef(User.class, principalService.getAuthPrincipal());
		postComment.setUser(user);
		postComment.setIsActive(true);

		postCommentDao.save(postComment);

		ConnHandler.commit();
		final PojoRes pojoRes = new PojoRes();
		pojoRes.setMessage("Success give comment!");
		return pojoRes;
	}
	
	public PojoRes saveLike(PojoPostLikeReq data) {
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
		pojoRes.setMessage("Liked posts!");
		return pojoRes;
	}
	
	public PojoRes saveBookmark(PojoPostBookmarkReq data) {
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
		pojoRes.setMessage("Bookmarked posts!");
		return pojoRes;
	}
	
}
