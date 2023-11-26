package io.dongvelop.actuatordemo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Endpoint(id = "myLibraryInfo")
public class MyLibraryInfoEndpoint {

    /**
     * "/actuator/myLibraryInfo" 로 GET 요청이 들어오면 이 메서드가 실행
     */
    @ReadOperation
    public List<LibraryInfo> getLibraryInofs(@Nullable final String name, final Boolean includeVersion) {

        final LibraryInfo libraryInfo = new LibraryInfo("logback", "1.0.0");
        final LibraryInfo libraryInfo2 = new LibraryInfo("jackson", "2.0.0");

        List<LibraryInfo> libraryInfos = Arrays.asList(libraryInfo, libraryInfo2);

        if (name != null) {
            libraryInfos = libraryInfos.stream()
                    .filter(libInfo -> libInfo.name().equals(name))
                    .toList();
        }

        if (Boolean.FALSE.equals(includeVersion)) {
            libraryInfos = libraryInfos.stream()
                    .map(lib -> new LibraryInfo(lib.name(), null))
                    .toList();
        }

        return libraryInfos;
    }

    /**
     * Request Body로 POST 요청 받았을 때 동작.
     * @Endpoint에서는 DTO 같은 객체를 받을 수 없어서 아래와 같이 필요한 속성들을 하나씩 나열해야함
     */
    @WriteOperation
    public void changeSomething(final String name, final Boolean enableSomething) {

        log.info("name[{}], enableSomething[{}]", name, enableSomething);
    }

    /**
     * /actuator/myLibraryInfo/{path} 와 같은 동작
     * @Selector 기본 동작은 MATCH.SINGLE (단일 path 값만 받음)
     * 여러 path variable을 받고 싶을 경우 ALL_REMAINING로 설정
     */
    @ReadOperation
    public String getPathVariable(@Selector(match = Selector.Match.ALL_REMAINING) final String... path) {

        log.info("path[{}]", Arrays.asList(path));
        return "path : " + Arrays.asList(path);
    }
}
