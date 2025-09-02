function loadTasks() {
    fetch('/api/tasks')
        .then(res => res.json())
        .then(data => {
            if (data.length === 0) {
                document.getElementById('tasks').innerHTML = "<p>No hay tareas registradas</p>";
                return;
            }
            let html = "<ul>";
            data.forEach(t => {
                html += `<li><b>${t.title}</b> - ${t.description} [${t.done ? "✅ Completada" : "⏳ Pendiente"}]</li>`;
            });
            html += "</ul>";
            document.getElementById('tasks').innerHTML = html;
        })
        .catch(err => {
            document.getElementById('tasks').innerHTML = "<p>Error al cargar tareas</p>";
            console.error(err);
        });
}

function addTask() {
    const task = {
        title: document.getElementById('title').value,
        description: document.getElementById('description').value,
        done: document.getElementById('done').value
    };

    fetch('/api/tasks', {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(task)
    })
        .then(res => res.text())
        .then(msg => {
            document.getElementById('postresp').innerText = msg;
            loadTasks(); // refrescar lista
        })
        .catch(err => {
            document.getElementById('postresp').innerText = "Error al agregar tarea";
            console.error(err);
        });
}