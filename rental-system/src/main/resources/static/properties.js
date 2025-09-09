const tbody = document.getElementById('props-body');
const btnAll = document.getElementById('btn-all');
const btnAvail = document.getElementById('btn-available');

async function load(url) {
    tbody.innerHTML = '<tr><td colspan="6">Φόρτωση...</td></tr>';
    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error('HTTP ' + res.status);
        const data = await res.json();
        if (!Array.isArray(data)) throw new Error('Μη αναμενόμενα δεδομένα');

        if (data.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6">Δεν βρέθηκαν ακίνητα.</td></tr>';
            return;
        }

        tbody.innerHTML = data.map(p => `
      <tr>
        <td>${p.id ?? ''}</td>
        <td>${p.name ?? ''}</td>
        <td>${p.location ?? ''}</td>
        <td>${p.price ?? ''}</td>
        <td>${p.available ? 'Ναι' : 'Όχι'}</td>
        <td>
          <a href="/ui/properties/${p.id}/edit">Επεξεργασία</a>
          &nbsp;|&nbsp;
          <a href="#" data-id="${p.id}" class="link-del">Διαγραφή</a>
        </td>
      </tr>
    `).join('');

        // attach delete handlers
        document.querySelectorAll('.link-del').forEach(a => {
            a.addEventListener('click', async (e) => {
                e.preventDefault();
                const id = a.getAttribute('data-id');
                if (!id) return;
                if (!confirm('Διαγραφή ακινήτου #' + id + ' ?')) return;
                const delRes = await fetch(`/properties/${id}`, { method: 'DELETE' });
                if (delRes.ok) {
                    // refresh current view
                    if (current === 'all') load('/properties');
                    else load('/properties/available');
                } else {
                    alert('Αποτυχία διαγραφής (HTTP ' + delRes.status + ')');
                }
            });
        });

    } catch (err) {
        tbody.innerHTML = `<tr><td colspan="6">Σφάλμα: ${err.message}</td></tr>`;
    }
}

let current = 'all';
btnAll?.addEventListener('click', () => { current = 'all'; load('/properties'); });
btnAvail?.addEventListener('click', () => { current = 'avail'; load('/properties/available'); });

// αρχικό load
load('/properties');
