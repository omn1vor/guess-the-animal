package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;

enum Format {
    JSON,
    XML,
    YAML
}

public class ImportExport {

    public static Format getFormat(String[] args) {
        String argString = String.join(" ", args);
        if (argString.matches("(?i)-type xml")) {
            return Format.XML;
        } else if (argString.matches("(?i)-type yaml")) {
            return Format.YAML;
        } else {
            return Format.JSON;
        }
    }

    public static Node Import(Format format) {
        File file = new File(getFileName(format));
        if (!file.isFile()) {
            return null;
        }
        Node root;
        ObjectMapper mapper = getMapper(format);
        try {
            root = mapper.readValue(file, Node.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Deque<Node> stack = new ArrayDeque<>();
        stack.offer(root);

        while (stack.size() > 0) {
            Node node = stack.poll();

            Node[] children = new Node[2];
            children[0] = node.getLeft();
            children[1] = node.getRight();

            for (Node child : children) {
                if (child != null) {
                    child.setParent(node);
                    stack.offer(child);
                }
            }
        }
        return root;
    }

    public static void Export(Format format, Node node) {
        File file = new File(getFileName(format));
        ObjectMapper mapper = getMapper(format);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileName(Format format) {
        return "animals_" + Locale.getDefault().getLanguage() + "." + format.name().toLowerCase();
    }

    private static ObjectMapper getMapper(Format format) {
        switch (format) {
            case XML:
                return new XmlMapper();
            case YAML:
                return new YAMLMapper();
            default:
                return new JsonMapper();
        }
    }

}
