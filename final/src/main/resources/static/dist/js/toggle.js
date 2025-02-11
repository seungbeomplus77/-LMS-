  const toggleBtn = document.querySelector('.menu-toggle-btn');
  const nav = document.querySelector('.left-nav');
  const links = nav.querySelectorAll('.nav-left');

  // 이전 메뉴 상태 복원
  if (localStorage.getItem('navOpen') === 'true') {
    nav.classList.add('open');
    toggleBtn.textContent = '< Menu';
  }

  // 토글 버튼 클릭 시 메뉴 여닫기
  toggleBtn.addEventListener('click', () => {
    nav.classList.toggle('open');
    if (nav.classList.contains('open')) {
      localStorage.setItem('navOpen', 'true');
      toggleBtn.textContent = '< Menu';
    } else {
      localStorage.setItem('navOpen', 'false');
      toggleBtn.textContent = '> Menu';
    }
  });

  // 현재 페이지 링크 강조
  links.forEach(link => {
    if (link.href === window.location.href) {
      link.classList.add('active-link');
    }
  });