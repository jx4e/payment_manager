function setupDropdown(ddId) {
    const button = document.getElementById(ddId + '-btn');
    const list = document.getElementById(ddId + '-list');
    const hiddenInput = document.getElementById(ddId + '-currency');

    if (!button || !list || !hiddenInput) return;

    button.addEventListener('click', () => {
        const expanded = button.getAttribute('aria-expanded') === 'true';
        button.setAttribute('aria-expanded', (!expanded).toString());
        list.classList.toggle('hidden');
    });

    list.querySelectorAll('li').forEach(item => {
        item.addEventListener('click', () => {
            const selectedText = item.textContent;
            const selectedCurrency = item.getAttribute('data-currency');

            // Update button display text
            button.querySelector('span > span').textContent = selectedText;

            // Update hidden input value
            hiddenInput.value = selectedCurrency;

            // Close dropdown
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
