function toggleModal(popupId) {
    const modal = document.getElementById(popupId);
    if (modal) {
        modal.classList.toggle('hidden');
    }
}

document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        const openModals = document.querySelectorAll('.modal:not(.hidden)');
        openModals.forEach(modal => toggleModal(modal.id));
    }
});
