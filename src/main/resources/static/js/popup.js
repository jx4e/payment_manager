function toggleModal(popupId) {
    const modal = document.getElementById(popupId);
    if (modal) {
        modal.classList.toggle('hidden');
    }
}
