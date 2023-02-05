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
  fetch('/api/parcels/mine', {redirect: 'error'})
    .then(r => r.json())
    .catch(e => {
      window.location.href = '/app/login'
    })
    .then(data => {
      updateParcelle(data)
    })
}

fetchMe = () => {
  fetch('/api/users/me')
    .then(r => {
      if (r.status !== 200) {
        window.location.href = '/app/login'
      }

      return r.json()
    })
    .catch(e => {
      window.location.href = '/app/login'
    })
    .then(data => {
      fetchParcelle()

      const name = data?.publicUser?.userName
      const tag = data?.userTag
      document.getElementById('user-greetings').innerHTML = `Bonjour ${name} (Utilise ${tag} pour te reconnecter) ` + document.getElementById('user-greetings').innerHTML // todo yohann fait mieux
    })
}

updateParcelle = (parcelle) => {
  console.log(parcelle)
  displayResourcesNb(parcelle)

  updateDisplay(parcelle)
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

setInterval(fetchParcelle, 5000)


// GAME VIEW

const view = document.getElementById('gameview')
const trees = []
const fruits = []
const irons = []

updateDisplay = (parcelle) => {

  if (parcelle?.naturalResources?.trees) {
    const treeNb = parcelle.naturalResources?.trees

    while (Math.ceil(treeNb / 100) < trees.length) {
      removeResource(trees, trees.length - (Math.ceil(treeNb / 100)))
    }
    if (Math.ceil(treeNb / 100) > trees.length) {
      generateTrees(Math.ceil(treeNb / 100) - trees.length)
    }
  }

  if (parcelle?.naturalResources?.fruits) {
    const fruitsNb = parcelle.naturalResources?.fruits

    while (Math.ceil(fruitsNb / 100) < fruits.length) {
      removeResource(fruits, fruits.length - (Math.ceil(fruitsNb / 100)))
    }
    if (Math.ceil(fruitsNb / 100) > fruits.length) {
      generateFruits(Math.ceil(fruitsNb / 100) - fruits.length)
    }
  }

  if (parcelle?.naturalResources?.iron) {
    const ironNb = parcelle.naturalResources?.iron

    while (Math.ceil(ironNb / 100) < irons.length) {
      removeResource(irons, irons.length - (Math.ceil(ironNb / 100)))
    }
    if (Math.ceil(ironNb / 100) > irons.length) {
      generateIron(Math.ceil(ironNb / 100) - irons.length)
    }
  }

  displaySprites()
}

generateTrees = (number) => {

  for (let i = 0; i < number; ++i) {
    let tree = {}
    tree.id = trees.length
    tree.obj = document.createElement('img')
    tree.obj.id = 'tree' + tree.id
    tree.obj.classList.add('tree')
    tree.obj.src = `/assets/trees/tree${Math.floor(Math.random() * 8)}.png`

    const modifier = 300
    tree.zIndex = Math.floor(Math.random() * modifier)

    tree.obj.style.zIndex = tree.zIndex
    const parallax = 25
    tree.obj.style.height = 'calc(20vh - ' + (parallax-(tree.zIndex / modifier)*parallax) + 'px)'

    tree.obj.style.left = 'calc(' + Math.floor(Math.random() * window.innerWidth) + 'px - 5vh)'
    tree.obj.style.bottom = 'calc(35% - ' + (tree.zIndex - modifier/2) + 'px)'

    trees[tree.id] = tree
  }
}

generateFruits = (number) => {

  for (let i = 0; i < number; ++i) {
    let fruit = {}
    fruit.id = fruits.length
    fruit.obj = document.createElement('img')
    fruit.obj.id = 'fruits' + fruit.id
    fruit.obj.classList.add('fruits')
    fruit.obj.src = `/assets/fruits/fruits${Math.floor(Math.random() * 4)}.png`


    const modifier = 300
    fruit.zIndex = Math.floor(Math.random() * modifier)

    fruit.obj.style.zIndex = fruit.zIndex
    const parallax = 25
    fruit.obj.style.height = 'calc(5vh - ' + (parallax-(fruit.zIndex / modifier)*parallax) + 'px)'

    fruit.obj.style.left = 'calc(' + Math.floor(Math.random() * window.innerWidth) + 'px - 5vh)'
    fruit.obj.style.bottom = 'calc(35% - ' + (fruit.zIndex - modifier/2) + 'px)'

    fruits[fruit.id] = fruit
  }
}

generateIron = (number) => {

  for (let i = 0; i < number; ++i) {
    let iron = {}
    iron.id = irons.length
    iron.obj = document.createElement('img')
    iron.obj.id = 'iron' + iron.id
    iron.obj.classList.add('iron')
    iron.obj.src = `/assets/iron/iron${Math.floor(Math.random() * 6)}.png`

    const modifier = 300
    iron.zIndex = Math.floor(Math.random() * modifier)

    iron.obj.style.zIndex = iron.zIndex
    const parallax = 25
    iron.obj.style.height = 'calc(6vh - ' + (parallax-(iron.zIndex / modifier)*parallax) + 'px)'

    iron.obj.style.left = 'calc(' + Math.floor(Math.random() * window.innerWidth) + 'px - 5vh)'
    iron.obj.style.bottom = 'calc(35% - ' + (iron.zIndex - modifier/2)  + 'px)'

    irons[iron.id] = iron
  }
}

displaySprites = () => {
  for (let i = 0; i < trees.length; ++i) {
    view.appendChild(trees[i].obj)
  }
  for (let i = 0; i < fruits.length; ++i) {
    view.appendChild(fruits[i].obj)
  }
  for (let i = 0; i < irons.length; ++i) {
    view.appendChild(irons[i].obj)
  }
}

removeResource = (array, number) => {
  for (let i = 0; i < number; ++i) {
    view.removeChild(array[array.length - 1].obj)
    array.pop()
  }
}