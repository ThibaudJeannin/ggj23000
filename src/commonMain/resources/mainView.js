document.getElementById('strip_1').addEventListener('click', () => {
  document.getElementById('sidebar').classList.toggle('opened')
  document.getElementById('strip_1').classList.toggle('opened')
})

document.getElementById('axe').addEventListener('click', () => {
  fetch('/api/parcels/mine/harvest/wood')
    .then(r => r.json())
    .then(data => {
      updateParcelle(data)
    })
})
document.getElementById('hoe').addEventListener('click', () => {
  fetch('/api/parcels/mine/harvest/fruits')
    .then(r => r.json())
    .then(data => {
      updateParcelle(data)
    })
})
document.getElementById('pickaxe').addEventListener('click', () => {
  fetch('/api/parcels/mine/harvest/iron')
    .then(r => r.json())
    .then(data => {
      updateParcelle(data)
    })
})

fetchParcelle = () => {
  fetch('/api/parcels/mine')
    .then(r => r.json())
    .then(data => {
      updateParcelle(data)
    })
}

fetchMe = () => {
  fetch('/api/users/me')
    .then(r => r.json())
    .then(data => {
      console.log(data)
    })
}

updateParcelle = (parcelle) => {
  console.log(parcelle)
}

fetchMe()
fetchParcelle()






