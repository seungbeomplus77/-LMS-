<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>교사 시간표</title>
  <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
  <style>
    /* 메인 레이아웃: 왼쪽은 캘린더, 오른쪽은 교사 명단 */
    .content-wrapper {
      display: flex;
      justify-content: space-between;
      padding: 20px;
    }
    .calendar-container {
      flex: 1;
      width: calc(100% - 250px);
      max-width: 1000px;
      margin-right: 20px;
    }
    #external-events {
      width: 200px;
      border: 1px solid #ddd;
      padding: 10px;
      background: #f8f8f8;
    }
    #external-events h4 {
      margin-top: 0;
      text-align: center;
    }
    .teacher {
      padding: 8px;
      text-align: center;
      border: 1px solid #ccc;
      background: #fff;
      margin-bottom: 5px;
      border-radius: 4px;
      cursor: pointer;
    }

    /* 달력 디자인 (FullCalendar 커스터마이징) */
    #calendar {
      background: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.15);
      padding: 15px;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .fc-toolbar {
      margin-bottom: 15px;
    }
    .fc-toolbar-title {
      font-size: 1.5rem;
      font-weight: bold;
      color: #333;
    }
    .fc-button {
      background-color: #4CAF50;
      border: none;
      border-radius: 4px;
      color: #fff;
      padding: 5px 10px;
      font-size: 0.9rem;
      cursor: pointer;
    }
    .fc-button:hover {
      background-color: #45a049;
    }
    .fc-col-header-cell-cushion {
      font-size: 1rem;
      font-weight: bold;
      color: #555;
    }
    .fc-timegrid-slot-label-cushion {
      font-size: 0.9rem;
      color: #777;
    }

    /* 이벤트 스타일 수정: 내부 여백 및 적절한 최소 너비 적용 */
    .fc-event {
      background-color: #2196F3;
      border: none;
      border-radius: 4px;
      padding: 4px 8px;
      font-size: 0.85rem;
      color: #fff;
      white-space: normal;      /* 긴 이름 줄바꿈 가능 */
      word-wrap: break-word;    /* 긴 단어 줄바꿈 가능 */
      /* 필요에 따라 적당히 조정. 너무 크게 잡으면 화면이 깨질 수 있음 */
      min-width: 120px !important; 
      width: auto !important;
    }
    .fc-event:hover {
      background-color: #1976D2;
    }

  </style>
</head>
<body>
  <header>
    <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
  </header>
  <main>
    <jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
    <div class="breadcrumb">
      <div class="sub-breadcrumb">
        <div class="sub-sub-breadcrumb">
          <span>></span>
          <a href="#" class="font-size small fw-bold">교사 시간표</a>
        </div>
      </div>
    </div>
    
    <div class="container justify-content-center align-items-center">
      <div class="body-container ms-5">
        <div class="body-title d-flex justify-content-center align-items-center">
          <h3 class="me-5 "><i class="bi bi-megaphone"></i> 학습계획표</h3>
        </div>
        <div class="body-main ms-5">
          <div class="content-wrapper">
            <!-- 왼쪽: 캘린더 영역 -->
            <div class="calendar-container">
              <div id="calendar"></div>
            </div>
            <!-- 오른쪽: 교사 명단 영역 -->
            <div id="external-events">
              <h4>교사 명단</h4>
              <div class="teacher" data-event='{"title": "이승범(세계정치와 경제)"}'>이승범(세계정치와 경제)</div>
              <div class="teacher" data-event='{"title": "교사2 매우 긴 이름 예시 테스트"}'>교사2 매우 긴 이름 예시 테스트</div>
              <div class="teacher" data-event='{"title": "교사3"}'>교사3</div>
              <div class="teacher" data-event='{"title": "교사4"}'>교사4</div>
            </div>
          </div>
          <div id="scheduleLoading" style="display: none;">loading...</div>
        </div>
      </div>
    </div>
  </main>
  
  <!-- FullCalendar 스크립트 -->
  <script src="${pageContext.request.contextPath}/dist/vendor/fullcalendar6/dist/index.global.min.js"></script>
  <script src="${pageContext.request.contextPath}/dist/vendor/fullcalendar6/dist/interaction.global.min.js"></script>
  <script src="${pageContext.request.contextPath}/dist/vendor/fullcalendar6/dist/locales-all.global.min.js"></script>
  <script src="${pageContext.request.contextPath}/dist/js/dateUtil.js"></script>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
      // 교사 명단 드래그 기능 설정
      new FullCalendar.Draggable(document.getElementById('external-events'), {
        itemSelector: '.teacher',
        eventData: function(element) {
          var data = element.getAttribute('data-event');
          return data ? JSON.parse(data) : { title: element.innerText.trim() };
        }
      });

      // 캘린더 초기화
      var calendarEl = document.getElementById('calendar');
      var calendar = new FullCalendar.Calendar(calendarEl, {
        locale: 'ko',
        initialView: 'timeGridWeek',
        editable: true,
        droppable: true,
        slotMinTime: "08:00:00",
        slotMaxTime: "17:00:00",
        slotDuration: "00:30:00",
        slotLabelInterval: "01:00:00",
        allDaySlot: false,
        height: 'auto',
        slotLabelContent: function(info) {
          // 각 시간대에 보여줄 라벨 (예: 교시 구분)
          const periods = ['1교시','2교시','3교시','4교시','점심시간','5교시','6교시','7교시','동아리/자율'];
          let index = parseInt(info.date.getHours(), 10) - 8;
          return (index >= 0 && index < periods.length) ? periods[index] : '';
        },
        eventClick: function(info) {
          if (confirm("일정을 삭제하시겠습니까?")) {
            info.event.remove();
          }
        },
        drop: function(info) {
          // 드래그앤드롭 후 추가 작업
        },
        // 여기서 minWidth 2000px 등 과도한 설정을 제거합니다.
        eventDidMount: function(info) {
          // 필요 없다면 완전히 비워둡니다. 
          // (필요 시 여기에 Tooltip 설정 등 다른 로직 추가 가능)
        }
      });
      calendar.render();
    });
  </script>
</body>
</html>
