// summernote-init.js
$(document).ready(function() {
    // Summernote 에디터 초기화
    $('.summernote').each(function() {
        $(this).summernote({
            height: 300,           // 에디터 높이
            lang: 'ko-KR',         // 한국어
            placeholder: $(this).data('placeholder') || '내용을 입력하세요'
        });
    });
});
