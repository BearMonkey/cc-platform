package org.monkey.platform.auth.config;

import org.monkey.platform.auth.enums.TokenStorageEnum;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * TokenStorageEditor
 *
 * @author cc
 * @since 2024/6/7 11:56
 */
public class TokenStorageEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null && !text.isEmpty()) {
            try {
                TokenStorageEnum storageMethod = TokenStorageEnum.valueOf(text.toUpperCase());
                setValue(storageMethod);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid token storage method value. Valid values are: " +
                        Arrays.stream(TokenStorageEnum.values()).map(Object::toString).collect(Collectors.joining(", ")));
            }
        }
    }
}
