document.addEventListener('DOMContentLoaded', function() {
    const toggleButton = document.getElementById('toggleButton');
    const searchForm = document.getElementById('searchForm');

    toggleButton.addEventListener('click', function() {
        searchForm.classList.toggle('minimized');
        if (searchForm.classList.contains('minimized')) {
            toggleButton.textContent = '+';
        } else {
            toggleButton.textContent = '-';
        }
    });
});
