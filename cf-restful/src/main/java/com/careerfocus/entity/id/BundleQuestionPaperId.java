package com.careerfocus.entity.id;

/**
 * Created by sarath on 10/06/17.
 */
public class BundleQuestionPaperId {
    static {
        // register persistent class in JVM
        try { Class.forName("org.superbiz.model.BundleQuestionPaper"); }
        catch(Exception e) {}
    }

    public int bundleId;
    public int questionPaperId;

    public BundleQuestionPaperId() {
    }

    public BundleQuestionPaperId(String str) {
        fromString(str);
    }

    public int getBundleId() {
        return bundleId;
    }

    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }

    public int getQuestionPaperId() {
        return questionPaperId;
    }

    public void setQuestionPaperId(int questionPaperId) {
        this.questionPaperId = questionPaperId;
    }

    public String toString() {
        return String.valueOf(bundleId)
                + "::" + String.valueOf(questionPaperId);
    }

    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + bundleId;
        rs = rs * 37 + questionPaperId;
        return rs;
    }

    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass() != getClass())
            return false;

        BundleQuestionPaperId other = (BundleQuestionPaperId) obj;
        return (bundleId == other.bundleId)
                && (questionPaperId == other.questionPaperId);
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        bundleId = Integer.parseInt(str);
        str = toke.nextToken();
        questionPaperId = Integer.parseInt(str);
    }

    protected static class Tokenizer {
        private final String str;
        private int last;

        public Tokenizer (String str) {
            this.str = str;
        }

        public String nextToken () {
            int next = str.indexOf("::", last);
            String part;
            if(next == -1) {
                part = str.substring(last);
                last = str.length();
            } else {
                part = str.substring(last, next);
                last = next + 2;
            }
            return part;
        }
    }
}
