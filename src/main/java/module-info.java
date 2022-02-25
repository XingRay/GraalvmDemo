module GraalvmDemo {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    exports com.xingray.graalvm to javafx.graphics;

    exports com.xingray.graalvm.controller to javafx.fxml;
    opens com.xingray.graalvm.controller to javafx.fxml;

    exports com.xingray.graalvm.domain to com.google.gson;
    opens com.xingray.graalvm.domain to com.google.gson;
}