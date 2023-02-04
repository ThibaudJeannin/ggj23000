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
      const name = data?.publicUser?.userName
      const tag = data?.userTag
      document.getElementById('user').innerText = `Bonjour ${name} (Utilise ${tag}) pour te reconnecter` // todo yohann fait mieux
    })
}

updateParcelle = (parcelle) => {
  console.log(parcelle)
  displayResourcesNb(parcelle)
}

displayResourcesNb = (parcelle) => {
  document.querySelector('#wood > .resource-text').innerText = formatResourceNb(parcelle?.resourceStorage?.wood?.quantity)
  document.querySelector('#fruits > .resource-text').innerText = formatResourceNb(parcelle?.resourceStorage?.fruits?.quantity)
  document.querySelector('#iron > .resource-text').innerText = formatResourceNb(parcelle?.resourceStorage?.iron?.quantity)
}


formatResourceNb = (nb) => {
  return nb != null && nb ? new Intl.NumberFormat('fr-FR', {maximumFractionDigits: 0}).format(nb) : 0
}

fetchMe()
fetchParcelle()






