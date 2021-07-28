package com.webold.client.enums;

import com.webold.framework.utility.view.Message;

public enum ClientException implements Message {

    CLIENT_EXCEPTION {
        @Override
        public String key() {
            return this.name();
        }
    }
}
