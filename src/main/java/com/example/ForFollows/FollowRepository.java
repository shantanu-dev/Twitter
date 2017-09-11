package com.example.ForFollows;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */

public interface FollowRepository extends JpaRepository<Follows ,String> {
    List<Follows> findByFid(int Fid);
    List<Follows> findByFollower(String follower);
    List<Follows>  findByFollowing(String  following);

}
