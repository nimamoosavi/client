package com.nicico.cost.client.enums;

import com.nicico.cost.framework.utility.response.Message;

public enum ClientException implements Message {

    CLIENT_EXCEPTION {
        @Override
        public String key() {
            return this.name();
        }
    }
}
