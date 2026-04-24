import { useState } from "react";
import type { Person } from "../models/Person";

function AddPerson() {
  const [newPerson, setNewPerson] = useState<Person>({
    firstName: "",
    lastName: "",
    birthDate: "",
    nationality: "",
    results: [
      {
        event: "",
        result: 0
      }
    ]
  });

  const addPerson = () => {
    fetch("http://localhost:8080/persons", {
      method: "POST",
      body: JSON.stringify(newPerson),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(() => alert("Sportlane lisatud!"))
      .catch(error => console.error("Add error:", error));
  };

  return (
    <div>
      <h2>Lisa sportlane</h2>

      <label>Eesnimi</label> <br />
      <input
        onChange={(e) => setNewPerson({ ...newPerson, firstName: e.target.value })}
        type="text"
      /> <br />

      <label>Perenimi</label> <br />
      <input
        onChange={(e) => setNewPerson({ ...newPerson, lastName: e.target.value })}
        type="text"
      /> <br />

      <label>Sünnikuupäev</label> <br />
      <input
        onChange={(e) => setNewPerson({ ...newPerson, birthDate: e.target.value })}
        type="date"
      /> <br />

      <label>Rahvus</label> <br />
      <input
        onChange={(e) => setNewPerson({ ...newPerson, nationality: e.target.value })}
        type="text"
      /> <br />

      <label>Ala</label> <br />
      <select
      onChange={(e) =>
          setNewPerson({
          ...newPerson,
          results: [{ ...newPerson.results[0], event: e.target.value }]
          })
      }
      >
      <option value="">Vali ala</option>
      <option value="M100">100m</option>
      <option value="LONG_JUMP">Kaugushüpe</option>
      <option value="SHOT_PUT">Kuulitõuge</option>
      <option value="HIGH_JUMP">Kõrgushüpe</option>
      <option value="M400">400m</option>
      <option value="HURDLES_110">110m tõkkejooks</option>
      <option value="DISCUS_THROW">Kettaheide</option>
      <option value="POLE_VAULT">Teivashüpe</option>
      <option value="JAVELIN_THROW">Odavise</option>
      <option value="M1500">1500m</option>
      </select>

      <label>Tulemus</label> <br />
      <input
        onChange={(e) =>
          setNewPerson({
            ...newPerson,
            results: [{ ...newPerson.results[0], result: Number(e.target.value) }]
          })
        }
        type="number"
      /> <br />

      <button onClick={addPerson}>Lisa sportlane</button>
    </div>
  );
}

export default AddPerson;