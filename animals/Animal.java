package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Animal {
    private String name;
    private String article;

    public Animal() {

    }

    public Animal(String input) {
        this();
        processName(input);
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @JsonIgnore
    public String getDefinedName() {
        return Vocabulary.res.getString("DefinedArticle") + " " + name;
    }

    @JsonIgnore
    public String getUndefinedName() {
        return article + ("".equals(article) ? "" : " ") + name;
    }

    @Override
    public String toString() {
        return getName();
    }

    private void processName(String input) {
        input = input.toLowerCase().strip();

        Pattern pattern;
        Matcher matcher;
        String articleRegex = Vocabulary.res.getString("UndefinedArticleRegex");
        if (!"".equals(articleRegex)) {
            pattern = Pattern.compile(String.format("(?i)^\\s*(%s)\\s+(\\w+.*)", articleRegex));
            matcher = pattern.matcher(input);
            if (matcher.matches()) {
                article = matcher.group(1);
                name = matcher.group(2);
                return;
            }
        }

        articleRegex = Vocabulary.res.getString("DefinedArticle");
        pattern = Pattern.compile(String.format("(?i)^\\s*%s\\s+(\\w+.*)", articleRegex));
        matcher = pattern.matcher(input);
        if (matcher.matches()) {
            name = matcher.group(1).strip();
        } else {
            name = input;
        }
        fillArticle();
    }

    private void fillArticle() {
        article = Vocabulary.getLogic("getUndefinedArticle", name);
    }
}
