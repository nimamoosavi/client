package app.ladderproject.client.enums;


import app.ladderproject.core.utility.view.Message;

public enum ClientException implements Message {

    CLIENT_EXCEPTION {
        @Override
        public String key() {
            return this.name();
        }
    }
}
