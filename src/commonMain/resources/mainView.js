document.getElementById('strip_1').addEventListener('click', () => {
  document.getElementById('sidebar').classList.toggle('opened')
  document.getElementById('strip_1').classList.toggle('opened')
})


fetchParcelle = () => {
  fetch('api/parcels/mine')
    .then(r => r.json())
    .then(data => {
      console.log(data)
    })
}

fetchMe = () => {
  fetch('api/users/me')
    .then(r => r.json())
    .then(data => {
      console.log(data)
    })
}

fetchMe()
fetchParcelle()






