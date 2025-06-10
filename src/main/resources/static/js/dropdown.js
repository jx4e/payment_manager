function setupDropdown(ddId) {
    const button = document.getElementById(ddId + '-btn');
    const list = document.getElementById(ddId + '-list');

    if (!button || !list) return;

    button.addEventListener('click', () => {
        const expanded = button.getAttribute('aria-expanded') === 'true';
        button.setAttribute('aria-expanded', (!expanded).toString());
        list.classList.toggle('hidden');
    });

    list.querySelectorAll('li').forEach(item => {
        item.addEventListener('click', () => {
            button.firstElementChild.textContent = item.textContent;
            button.setAttribute('aria-expanded', 'false');
            list.classList.add('hidden');
        });
    });

    document.addEventListener('click', e => {
        if (!button.contains(e.target) && !list.contains(e.target)) {
            button.setAttribute('aria-expanded', 'false');
            list.classList.add('hidden');
        }
    });
}