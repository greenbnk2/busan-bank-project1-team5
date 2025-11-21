/*
    날짜 : 2025-11-17
    내용 : admin aside js
*/
document.addEventListener('DOMContentLoaded', function () {

    const body = document.body;
    const aside = document.querySelector('aside');
    const asideSwitch = document.querySelector('.aside-switch input[type="checkbox"]');
    const menuTitles = Array.from(document.querySelectorAll('.menu-title'));

    // 스위치 상태를 확인하여 aside 초기 상태를 설정
    if(asideSwitch && asideSwitch.checked) {
        body.classList.add('sb-expanded');
        body.classList.add('sb-pinned');
    } else {
        body.classList.remove('sb-expanded');
        body.classList.remove('sb-pinned');
    }

    // ---- 1. 사이드바 확장/축소 및 고정 기능 ----

    // 1-1. 스위치 (영구 고정) 로직
    if (asideSwitch) {
        asideSwitch.addEventListener('change', function () {
            console.log('스위치 클릭 이벤트 발생');
            if (this.checked) {
                body.classList.add('sb-expanded');  // 사이드바 너비 확장 (애니메이션)
                body.classList.add('sb-pinned');    // 메인 콘텐츠 밀림 활성화 (고정 상태)
                aside.classList.add('sb-fixed');    // 마우스 오버 방해용
                console.log('초기 스위치 체크됨');
            } else {
                body.classList.remove('sb-expanded');
                body.classList.remove('sb-pinned');
                aside.classList.remove('sb-fixed');
            }
        });
    }

    // 1-2. 마우스 오버 (일시적 확장) 로직
    if(aside) {
        // 마우스가 영역 안으로 오면 확대
        aside.addEventListener('mouseenter', function () {
            if(!asideSwitch.checked) {
                body.classList.add('sb-expanded');
            }
        });

        // 마우스가 영역 밖으로 가면 축소
        aside.addEventListener('mouseleave', function () {
            if(!asideSwitch.checked) {
                body.classList.remove('sb-expanded');
            }

            // aside를 벗어나면 확장 여부(고정 아닐 때) 모든 서브메뉴 닫기
            if (!asideSwitch.checked) {
                menuTitles.forEach(menuTitle => {
                    menuTitle.classList.remove('active-menu');
                    const submenu = menuTitle.parentElement.querySelector('.submenu');
                    if (submenu) {
                        submenu.style.maxHeight = null;
                    }
                });
            }

        });
    }

    // ---- 2. 서브메뉴 드롭다운 토글 기능 ----

    menuTitles.forEach(function (menuTitle) {
        menuTitle.addEventListener('click', function (e) {
            e.preventDefault();
            // .menu-title 바로 다음 요소가 아니라, html 구조상, A태그의 부모 li를 찾고, 그 다음 형제요소(ul.submenu)를 찾음
            const submenu = this.parentElement.querySelector('.submenu');

            // 다른 메뉴가 열려 있다면 닫음 (Accordion 기능)
            menuTitles.forEach(otherTitle => {
                const otherSubmenu = otherTitle.parentElement.querySelector('.submenu');

                if (otherTitle !== this) {
                    otherTitle.classList.remove('active-menu');
                    if(otherSubmenu) {
                        otherSubmenu.style.maxHeight = null;
                    }
                }
            });

            // 현재 메뉴 토글
            this.classList.toggle('active-menu');
            if (submenu) {
                if (submenu.style.maxHeight) {
                    // 열려 있으면 닫기
                    submenu.style.maxHeight = null;
                } else {
                    // 닫혀 있으면 열기 (스크롤 높이를 이용해 정확한 애니메이션 구현)
                    submenu.style.maxHeight = 'fit-content'
                    let finalHeigh = submenu.scrollHeight;
                    console.log('최종 높이 측정값:', finalHeigh);
                    submenu.style.maxHeight = submenu.scrollHeight + 'px';
                }
            }
        });
    });

    // --- 3. 2차 메뉴 클릭 시 동작 ---
    document.querySelectorAll('.submenu a').forEach(function(submenuLink) {
        submenuLink.addEventListener('click', function(e) {
            // 페이지 이동은 기본적으로 허용합니다.

            // 모든 2차 메뉴 링크의 active-submenu 클래스를 제거
            document.querySelectorAll('.submenu a').forEach(link => {
                link.classList.remove('active-submenu');
            });

            //  현재 클릭된 링크에 active-submenu 클래스를 추가 (활성화 상태)
            this.classList.add('active-submenu');

            // 고정 상태가 아니면 페이지 이동 후 사이드바를 축소합니다.
            if (!asideSwitch || !asideSwitch.checked) {
                body.classList.remove('sb-expanded');
            }
            // 만약 고정 상태라면 (sb-fixed가 있다면), sb-expanded가 유지됩니다.

            // 실제로 페이지가 이동될 것이므로, 이 클래스 제거는 다음 페이지 로드 시 적용됩니다.
            // 만약 SPA(Single Page Application)처럼 동작한다면, 여기에 실제 축소 로직을 넣어야 하지만,
            // 현재는 페이지 이동이 발생한다고 가정하고 클래스만 제거합니다.
        });
    });

});