package com.sparta.easyspring.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 909881718L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.sparta.easyspring.timestamp.QTimeStamp _super = new com.sparta.easyspring.timestamp.QTimeStamp(this);

    public final ListPath<com.sparta.easyspring.comment.entity.Comment, com.sparta.easyspring.comment.entity.QComment> commentList = this.<com.sparta.easyspring.comment.entity.Comment, com.sparta.easyspring.comment.entity.QComment>createList("commentList", com.sparta.easyspring.comment.entity.Comment.class, com.sparta.easyspring.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isNotice = createBoolean("isNotice");

    public final BooleanPath isPinned = createBoolean("isPinned");

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<com.sparta.easyspring.postlike.entity.PostLike, com.sparta.easyspring.postlike.entity.QPostLike> postLikeList = this.<com.sparta.easyspring.postlike.entity.PostLike, com.sparta.easyspring.postlike.entity.QPostLike>createList("postLikeList", com.sparta.easyspring.postlike.entity.PostLike.class, com.sparta.easyspring.postlike.entity.QPostLike.class, PathInits.DIRECT2);

    public final ListPath<PostMedia, QPostMedia> postMediaList = this.<PostMedia, QPostMedia>createList("postMediaList", PostMedia.class, QPostMedia.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final com.sparta.easyspring.auth.entity.QUser user;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sparta.easyspring.auth.entity.QUser(forProperty("user")) : null;
    }

}

