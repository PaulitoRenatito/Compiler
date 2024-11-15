package cefet.lexical.token;

public class FloatNumberToken extends Token {

        public final float value;

        public FloatNumberToken(float value) {
            super(TokenType.FLOAT_CONSTANT);
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString() + "(" + value + ")";
        }
}
