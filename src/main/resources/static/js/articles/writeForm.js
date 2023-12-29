'use strict'

const Editor = toastui.Editor;
const { codeSyntaxHighlight } = Editor.plugin;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '500px',
    initialEditType: 'markdown',
    initialValue: '내용을 입력해 주세요.',
    previewStyle: 'vertical',
    plugins: [[codeSyntaxHighlight, { highlighter: Prism }]]
});

function form_submit(form) {
    form.title.value = form.title.value.trim();

    if (form.title.value.length === 0) {
        alert("제목을 입력해주세요");
        form.title.focus();
        return;
    }

    form.content.value = editor.getMarkdown().trim();
    if (form.content.value.length === 0) {
        alert("내용을 입력해주세요");
        editor.focus();
        return;
    }

    form.submit();

}
