package com.lian.base.dao;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lian.base.common.model.user.LoginUser;
import com.lian.base.common.util.UserInfoHolder;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;

/**
 * <p>
 * DB表默认属性自动填充处理器
 * </p>
 *
 * @author LianWenLong
 * @since 2022/7/20 16:43
 */
@RequiredArgsConstructor
public class MetaHandler implements MetaObjectHandler {

    public static final String CREATE_TIME = "createTime";
    public static final String CREATOR = "creator";
    public static final String MODIFY_TIME = "modifyTime";
    public static final String MODIFIER = "modifier";

    @Override
    public void insertFill(MetaObject metaObject) {
        Optional<LoginUser> loginUser = UserInfoHolder.getCurrentUser();
        insertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        insertFill(metaObject, MODIFY_TIME, LocalDateTime.class, LocalDateTime.now());
        loginUser.ifPresent(user -> {
            insertFill(metaObject, CREATOR, String.class, user.getName());
            insertFill(metaObject, MODIFIER, String.class, user.getName());
        });
    }

    private <T, E extends T> void insertFill(MetaObject metaObject, String fieldName, Class<T> fieldType, E fieldVal) {
        // 防止存在字段存在旧值，不自动填充。先将字段置为空值
        metaObject.setValue(fieldName, null);
        this.strictInsertFill(metaObject, fieldName, fieldType, fieldVal);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Optional<LoginUser> loginUser = UserInfoHolder.getCurrentUser();
        updateFill(metaObject, MODIFY_TIME, LocalDateTime.class, LocalDateTime.now());
        loginUser.ifPresent(user -> insertFill(metaObject, MODIFIER, String.class, user.getName()));
    }

    private <T, E extends T> void updateFill(MetaObject metaObject, String fieldName, Class<T> fieldType, E fieldVal) {
        metaObject.setValue(fieldName, null);
        this.strictUpdateFill(metaObject, fieldName, fieldType, fieldVal);
    }
}
