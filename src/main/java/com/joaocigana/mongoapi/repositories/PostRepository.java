package com.joaocigana.mongoapi.repositories;

import com.joaocigana.mongoapi.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ 'title': {$regex: ?0, $options: 'i'} }")
    List<Post> searchTitle(String text);

    @Query("{$and: [ { postDate: {$gte: ?1}}, { postDate: {$lte: ?2}}, {$or:[ {'title': {$regex: ?0, $options: 'i'} }, {'body': {$regex: ?0, $options: 'i'}}, {'comments.text': {$regex: ?0, $options: 'i'}}]} ] }")
    List<Post> searchByTextInDateRange(String text, LocalDateTime minDate, LocalDateTime maxDate);

}
