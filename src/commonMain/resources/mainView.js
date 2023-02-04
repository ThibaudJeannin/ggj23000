document.getElementById('strip_1').addEventListener('click', () => {
  document.getElementById('sidebar').classList.toggle('opened')
  document.getElementById('strip_1').classList.toggle('opened')
})

document.getElementById('perk_1').addEventListener('click', () => {
  document.getElementById('subsidebar_1').classList.toggle('opened')
  document.getElementById('subsidebar_2').classList.remove('opened')
  document.getElementById('subsidebar_3').classList.remove('opened')
  document.getElementById('subsidebar_4').classList.remove('opened')
})
document.getElementById('perk_2').addEventListener('click', () => {
  document.getElementById('subsidebar_2').classList.toggle('opened')
  document.getElementById('subsidebar_1').classList.remove('opened')
  document.getElementById('subsidebar_3').classList.remove('opened')
  document.getElementById('subsidebar_4').classList.remove('opened')
})
document.getElementById('perk_3').addEventListener('click', () => {
  document.getElementById('subsidebar_3').classList.toggle('opened')
  document.getElementById('subsidebar_1').classList.remove('opened')
  document.getElementById('subsidebar_2').classList.remove('opened')
  document.getElementById('subsidebar_4').classList.remove('opened')
})
document.getElementById('perk_4').addEventListener('click', () => {
  document.getElementById('subsidebar_4').classList.toggle('opened')
  document.getElementById('subsidebar_1').classList.remove('opened')
  document.getElementById('subsidebar_2').classList.remove('opened')
  document.getElementById('subsidebar_3').classList.remove('opened')
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

  updateDisplay(parcelle)
}

displayResourcesNb = (parcelle) => {
  document.querySelector("#wood > .resource-text").innerText = formatResourceNb(parcelle?.resourceStorage?.wood?.quantity)
  document.querySelector("#fruits > .resource-text").innerText = formatResourceNb(parcelle?.resourceStorage?.fruits?.quantity)
  document.querySelector("#iron > .resource-text").innerText = formatResourceNb(parcelle?.resourceStorage?.iron?.quantity)
}


formatResourceNb = (nb) => {
  return nb != null && nb ? new Intl.NumberFormat("fr-FR", {maximumFractionDigits: 0}).format(nb) : 0

}

fetchMe()
fetchParcelle()

setInterval(fetchParcelle, 5 * 60 * 1000)


// GAME VIEW

const view = document.getElementById("gameview")
var trees = []
var fruits = []
var iron = []

updateDisplay = (parcelle) => {

  if (parcelle?.naturalResources?.trees) {

    while (Math.floor(parcelle.naturalResources?.trees/100) < trees.length) {
      removeTree(trees.length - (Math.floor(parcelle.naturalResources?.trees/100)))
    }
    if (Math.floor(parcelle.naturalResources?.trees/100) > trees.length) {
      generateTrees((Math.floor(parcelle.naturalResources?.trees/100)) - trees.length)
    }
  }
}

generateTrees = (number) => {

  for (let i = 0; i < number; ++i) {
    let tree = {};
    tree.id = trees.length;
    tree.obj = document.createElement('img')
    tree.obj.id = 'tree' + tree.id
    tree.obj.classList.add('tree')
    tree.obj.src = `/assets/tree${Math.floor(Math.random()*8)}.png`
    // tree.style.backgroundImage = `url("/assets/tree${Math.floor(Math.random()*8)}.png")`

    tree.zIndex = Math.floor(Math.random() * 50)

    tree.obj.style.zIndex = tree.zIndex

    tree.obj.style.left = 'calc(' + Math.floor(Math.random() * window.innerWidth) + 'px - 5vh)'
    tree.obj.style.top = 'calc(65% + ' + tree.zIndex + 'px' + ' - 20vh)'

    trees[tree.id] = tree
  }

  displaySprites()
}

displaySprites = () => {
  for (let i = 0; i < trees.length; ++i) {
    view.appendChild(trees[i].obj)
  }
}

removeTree = (number) => {
  for (let i = 0; i < number; ++i) {
    view.removeChild(trees[trees.length - 1].obj)
    trees.pop()
  }
}