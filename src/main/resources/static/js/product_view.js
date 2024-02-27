function toggleButtonStyle() {
        var buyButton = document.getElementById('addCart');
        console.log(buyButton);
        // Toggle the 'purchased-style' class
//        buyButton.classList.toggle('purchased-style');

        buyButton.style.backgroundColor = '#eb5412b3';
        buyButton.style.color = 'white';
        buyButton.style.cursor = 'pointer';
        // Add other styles as needed

        // Change the button text for purchased state
        buyButton.textContent = 'Into Cart';
}