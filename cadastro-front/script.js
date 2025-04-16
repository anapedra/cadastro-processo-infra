const API_URL = "http://localhost:8080/api/users";

// Função para buscar todos os usuários e exibir na lista
async function fetchUsers() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Failed to fetch users");
        const users = await response.json();

        const userList = document.getElementById("users");
        userList.innerHTML = ""; // Limpa a lista antes de exibir os usuários

        users.forEach((user) => {
            const li = document.createElement("li");
            li.textContent = `${user.nome}\nemail:(${user.email})\ncpf: ${user.cpf}\nidade:${user.idade}\n\n `;
            li.addEventListener("click", () => populateForm(user)); // Preenche o formulário ao clicar no usuário
            userList.appendChild(li);
        });
    } catch (error) {
        alert(`Erro ao buscar usuários: ${error.message}`);
    }
}

// Função para preencher o formulário com os dados do usuário
function populateForm(user) {
    document.getElementById("userId").value = user.id || "";
    document.getElementById("name").value = user.nome || "";
    document.getElementById("email").value = user.email || "";
    document.getElementById("cpf").value = user.cpf || "";
    document.getElementById("tel").value = user.tel || "";
    document.getElementById("birthDate").value = user.dataNascimento || "";
}

// Função para salvar ou atualizar um usuário
async function handleForm(event) {
    event.preventDefault();

    const id = document.getElementById("userId").value;
    const user = {
        nome: document.getElementById("name").value,
        email: document.getElementById("email").value,
        cpf: document.getElementById("cpf").value,
        tel: document.getElementById("tel").value,
        dataNascimento: document.getElementById("birthDate").value,
    };

    const options = {
        method: id ? "PUT" : "POST", // PUT para atualização, POST para criação
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
    };

    const url = id ? `${API_URL}/${id}` : API_URL;

    try {
        const response = await fetch(url, options);
        const responseText = await response.text();
    
        if (!response.ok) {
            let message = responseText;
    
            // Tenta extrair só a propriedade "message" se for JSON
            try {
                const json = JSON.parse(responseText);
                message = json.message || message;
            } catch (e) {
                // Se não for JSON, continua com a mensagem bruta
            }
    
            throw new Error(message);
        }
    
        alert("Usuário salvo com sucesso!");
        document.getElementById("userForm").reset();
        fetchUsers();
    } catch (error) {
        alert(`Erro ao salvar usuário: ${error.message}`);
    }
    
}

// Inicializa a lista de usuários ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    fetchUsers();
});
