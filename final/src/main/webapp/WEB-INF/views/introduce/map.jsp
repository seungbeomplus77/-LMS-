<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오시는길</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>

</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/header2.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
	<a href="#" class="font-size small">학교소식 > </a>
	<a href="#" class="font-size small fw-bold">오시는길</a> 	
  </div>
  </div>
</div>

	<div class="body-title d-flex justify-content-center">
	  <h3 class="me-5"><i class="bi bi-geo-alt"></i> 미래고등학교 오시는길</h3>
	</div>
    
    <div class="d-flex justify-content-center align-items-center">
        <div id="map" style="width:800px;height:400px; margin-top:10px"></div>
    </div>
    
    <div class="map-border" style="width: 55%; margin: 0 auto; margin-top:20px; border-top: 3px solid #424951;"></div>

<div class="d-flex justify-content-center align-items-center" style="margin-top: 20px;">
  <table class="table table-sm w-auto text-center" style="font-size: 0.8rem;">
    <tbody>
      <tr>
        <th scope="row"><i class="bi bi-geo-alt-fill"></i> 주소</th>
        <td>06289, 서울특별시 강남구 선릉로 118, 118 Seolleung-ro, Gangnam-gu, Seoul, 대치동 501</td>
      </tr>
      <tr>
        <th scope="row"><i class="bi bi-telephone-fill"></i> 전화번호</th>
        <td>(+82)02-1111-1111 (내선 교무실 -1, 행정실 -2, 생활관 -3, 입학문의 -0)</td>
      </tr>
      <tr>
        <th scope="row"><i class="bi bi-printer-fill"></i> 팩스</th>
        <td>교무실 팩스 : (+82)02-2222-2222 / 행정실 팩스 : (+82)02-3333-3333</td>
      </tr>
      <tr>
        <th scope="row"><i class="bi bi-bus-front-fill"></i> 대중교통</th>
        <td>지하철(3호선) 도곡역 3번출구 도보 10분 소요</td>
      </tr>
    </tbody>
  </table>
</div>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=54c043fce7073e3c8e8433c9424bbb3a"></script>
    <script>
        var mapContainer = document.getElementById('map');
          mapOption = {
            center: new kakao.maps.LatLng(37.4895717276467, 127.05704577914662),
            level: 3	
          };

        var map = new kakao.maps.Map(mapContainer, mapOption);
        
        // (1) 마커를 표시할 좌표
        var markerPosition = new kakao.maps.LatLng(37.4895717276467, 127.05704577914662);
        
        // (2) 마커 생성
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });

        // (3) 마커를 지도에 표시
        marker.setMap(map);
        
        // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성
        var mapTypeControl = new kakao.maps.MapTypeControl();

        // 지도에 컨트롤을 추가해야 지도위에 표시
        // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

        // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성
        var zoomControl = new kakao.maps.ZoomControl();
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    
    </script>
</main>

<footer class="mt-5 w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>