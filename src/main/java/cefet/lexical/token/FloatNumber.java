package cefet.lexical.token;

public class FloatNumber extends Token {

        public final float value;

        public FloatNumber(float value) {
            super(TokenType.FLOAT_CONSTANT);
            this.value = value;
        }

        @Override
        public String toString() {
            return super.toString() + "(" + value + ")";
        }
}
