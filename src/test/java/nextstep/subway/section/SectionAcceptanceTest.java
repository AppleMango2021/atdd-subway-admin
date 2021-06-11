package nextstep.subway.section;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.line.LineAcceptanceTest;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.station.StationAcceptanceTest;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.dto.StationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SectionAcceptanceTest extends AcceptanceTest {

    private final String BASE_REQUEST_URI = "/lines/";
    private final String SECTION_ADD_URI = "/sections";
    private final StationAcceptanceTest stationAcceptanceTest = new StationAcceptanceTest();
    private final LineAcceptanceTest lineAcceptanceTest = new LineAcceptanceTest();

    private LineResponse 사호선_생성_응답;
    private SectionRequest 서울_회현_요청;
    private SectionRequest 회현_명동_요청;
    private SectionRequest 서울_명동_요청;

    private Long 사호선_ID;
    private Station 서울역;
    private Station 회현역;
    private Station 명동역;

    @BeforeEach
    public void setUp() {
        super.setUp();

        회현역 = stationAcceptanceTest.지하철_역_등록되어_있음("회현역").as(Station.class);
        명동역 = stationAcceptanceTest.지하철_역_등록되어_있음("명동역").as(Station.class);
        서울역 = stationAcceptanceTest.지하철_역_등록되어_있음("서울역").as(Station.class);

        사호선_생성_응답 = lineAcceptanceTest.지하철_노선_등록되어_있음(new LineRequest("4호선", "bg-blue", 회현역.getId(), 명동역.getId(), 20))
                .as(LineResponse.class);

        사호선_ID = 사호선_생성_응답.getId();
        서울_회현_요청 = new SectionRequest(서울역.getId(), 회현역.getId(), 10);
        회현_명동_요청 = new SectionRequest(회현역.getId(), 명동역.getId(), 20);
        서울_명동_요청 = new SectionRequest(서울역.getId(), 명동역.getId(), 30);
    }

    @DisplayName("구간 등록")
    @Test
    void 구간등록() {
        // when
        //구간을_노선에_등록_요청
        ExtractableResponse<Response> response = 구간을_노선에_등록_요청(사호선_ID, 서울_회현_요청);

        // then
        //구간_등록_완료
        구간_등록_OK_응답(response);
        노선에_지하철이_포함되었는지_점검(response, 서울역);
        노선에_지하철이_포함되었는지_점검(response, 회현역);
        노선에_지하철이_포함되었는지_점검(response, 명동역);
    }

    @DisplayName("구간 등록 : 구간의 상행역이 상행종점")
    @Test
    void 구간등록_when_상행역이_상행종점() {
        // when
        //구간을_노선에_등록_요청
        ExtractableResponse<Response> response = 구간을_노선에_등록_요청(사호선_ID, 서울_회현_요청);

        // then
        //구간_등록_완료
        구간_등록_OK_응답(response);
        List<Long> expectedOrderId = Arrays.asList(서울역.getId(), 회현역.getId(), 명동역.getId());
        노선에_지하철이_순서대로_등록되었는지_점검(response, expectedOrderId);

    }

    @DisplayName("구간 등록 : 구간의 하행역이 하행종점")
    @Test
    void 구간등록_when_하행역이_하행종점() {
        // when
        //구간_생성_요청

        //구간을_노선에_등록_요청

        // then
        //구간_등록_완료
    }

    @DisplayName("구간 등록 : 역 사이에 새로운 역 등록(기존 상행역에 연결)")
    @Test
    void 구간등록_when_상행역에_신규_역_연결() {
        // when
        //구간_생성_요청

        //구간을_노선에_등록_요청

        // then
        //구간_등록_완료
    }

    @DisplayName("구간 등록 : 역 사이에 새로운 역 등록(기존 하행역에 연결)")
    @Test
    void 구간등록_when_하행역에_신규_역_연결() {
        // when
        //구간_생성_요청

        //구간을_노선에_등록_요청

        // then
        //구간_등록_완료
    }

    private void 구간_등록_OK_응답(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> 구간을_노선에_등록_요청(Long lineId, SectionRequest request) {
        return httpPost(BASE_REQUEST_URI + lineId + SECTION_ADD_URI, request);
    }

    private void 노선에_지하철이_포함되었는지_점검(ExtractableResponse<Response> response, Station station) {
        List<Long> stationId = response.as(LineResponse.class).getStationResponses().stream()
                .map(StationResponse::getId)
                .collect(Collectors.toList());

        assertThat(stationId).contains(station.getId());
    }

    private void 노선에_지하철이_순서대로_등록되었는지_점검(ExtractableResponse<Response> response, List<Long> stationIds) {
        List<Long> actualIds = response.as(LineResponse.class).getStationResponses().stream()
                .map(StationResponse::getId)
                .collect(Collectors.toList());

        assertThat(actualIds).containsExactlyElementsOf(stationIds);
    }
}
