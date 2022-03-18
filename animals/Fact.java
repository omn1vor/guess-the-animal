package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fact {
    private String verb;
    private String negativeVerb;
    private String feature;

    public Fact() {

    }

    public Fact(String input) {
        this();
        String fact = input.strip();
        Pattern pattern = Pattern.compile(Vocabulary.getStr("factFormat"));
        Matcher matcher = pattern.matcher(fact);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(Vocabulary.getStr("factFormatError"));
        }
        verb = matcher.group(1);
        feature = matcher.group(2);
        negativeVerb = produceNegativeVerb();
    }

    @JsonIgnore
    public String getFact(boolean isTrue) {
        return String.format("%s %s.", isTrue ? verb : negativeVerb, feature);
    }

    @JsonIgnore
    public String getQuestion() {
        String questionVerb = Vocabulary.getLogic("getQuestion", verb);
        return String.format("%s %s?", Vocabulary.capitalise(questionVerb), feature);
    }

    public String getVerb() {
        return verb;
    }

    public String getNegativeVerb() {
        return negativeVerb;
    }

    public String getFeature() {
        return feature;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public void setNegativeVerb(String negativeVerb) {
        this.negativeVerb = negativeVerb;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    private String produceNegativeVerb() {
        return Vocabulary.getLogic("getNegativeVerb", verb);
    }

}
