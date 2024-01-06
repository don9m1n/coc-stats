const Viewer = toastui.Editor;

const viewer = new Viewer({
    el: document.querySelector('#viewer'),
    height: '500px',
    initialValue: document.querySelector('.content').value,
    viewer: true
});
