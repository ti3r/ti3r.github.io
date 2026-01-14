// Intersection Observer for fade-in animations
document.addEventListener('DOMContentLoaded', function() {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('is-visible');
            }
        });
    }, {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    });

    // Observe all sections with fade-in-section class
    document.querySelectorAll('.fade-in-section').forEach(section => {
        observer.observe(section);
    });
});
