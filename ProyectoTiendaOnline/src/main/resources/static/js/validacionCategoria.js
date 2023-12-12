const form = document.getElementById('form');
		const nombre = document.getElementById('nombre');
		const errornombre = document.getElementById('errornombre');
		const exp = /^[a-zA-Z\s]{4,30}$/;
		form.addEventListener('submit', function (e) {
			e.preventDefault();

			if (!exp.test(nombre.value.trim()) || nombre.value === "") {
				errornombre.classList.toggle('clase');
				errornombre.innerText = 'Requiere mas de 4 carateres.';
				return;
			} else {
				errornombre.classList.toggle('clase');
				errornombre.innerText = '';
			}




			this.submit();
		});