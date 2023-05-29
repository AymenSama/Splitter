package splitter.interaction;

class NumericString {
    static boolean isNumeric(String string) {
        if (string == null) {
            throw new IllegalArgumentException("Provided string can't be null");
        }

        if (string.startsWith("-")) {
            string = string.substring(1);
        }

        String[] parts = string.split("\\.", 2);
        for (String part: parts) {
            if (part.equals("")) {
                return false;
            }

            boolean isNotNumeric = part.chars().anyMatch(c -> !Character.isDigit(c));
            if (isNotNumeric) {
                return false;
            }
        }

        return true;
    }
}
