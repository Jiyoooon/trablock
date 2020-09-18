package com.trablock.application;

import java.util.List;

import com.trablock.domain.Post;

public interface IPostService {
	public int registePost(Post post);				//글쓰기
    public Post searchPostById(String postId);		//글 상세 조회
    public List<Post> searchPosts(String title			//제목
    							, String destination	//여행지
    							, String range_start	//시작 금액
    							, String range_end		//끝 금액
    							, String userId			//유저 id
    							, String type);			//0 : 전체 포스팅, 1 : 내가 쓴 포스팅, 2 : 내 모임 포스팅
    
    public int updatePost(Post post);				//글 수정
    public int removePost(String postId, String userId);			//글 삭제

}
