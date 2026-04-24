import { useEffect, useState } from "react";
import type { Person } from "../models/Person";

function PersonsPage() {

  const [persons, setPersons] = useState<Person[]>([]);
  const [totalElements, setTotalElements] = useState(0); // mitu sportlast kokku
  const [totalPages, setTotalPages] = useState(0);       // mitu lehte kokku

  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [sort, setSort] = useState("id,asc");
  const [nationality, setNationality] = useState("");

  // päring backendile, vastus
  useEffect(() => {
    fetch(`http://localhost:8080/persons?page=${page}&size=${size}&sort=${sort}&nationality=${nationality}`)
      .then(res => res.json())
      .then(json => {
        setPersons(json.content);        // Page -> content
        setTotalElements(json.totalElements); // mitu kokku
        setTotalPages(json.totalPages);       // mitu lehte
      });
  }, [page, size, sort, nationality]);

  // mitu korraga
  const sizeHandler = (newSize: number) => {
    setSize(newSize);
    setPage(0); // alati tagasi esimesele lehele
  };

  // sorteerimine
  const sortHandler = (newSort: string) => {
    setSort(newSort);
    setPage(0);
  };

  // filter rahvuse järgi
  const nationalityHandler = (value: string) => {
    setNationality(value);
    setPage(0);
  };

  // kustutamine
  const deletePerson = (id: number) => {
    fetch("http://localhost:8080/persons/" + id, {
      method: "DELETE"
    })
      .then(() => {
        setPage(0);
      });
  };

  return (
    <div>

      <div>
        {page * size + 1}-
        {(page + 1) * size > totalElements ? totalElements : (page + 1) * size}
        {" "}kuvatud {totalElements}-st
      </div>

      <select defaultValue={5} onChange={(e) => sizeHandler(Number(e.target.value))}>
        <option>2</option>
        <option>3</option>
        <option>10</option>
      </select>

      <br /><br />

      {/*sorteerimine*/}
      <button onClick={() => sortHandler("firstName,asc")}>Eesnimi A-Z</button>
      <button onClick={() => sortHandler("firstName,desc")}>Eesnimi Z-A</button>
      <button onClick={() => sortHandler("totalPoints,desc")}>Punktid suuremast väiksemani</button>

      <br /><br />

      {/*rahvus*/}
      <input
        placeholder="Filtreeri rahvuse järgi"
        value={nationality}
        onChange={(e) => nationalityHandler(e.target.value)}
      />

      <br /><br />

      {/* sportlased */}
      {persons.map(person => (
      <div key={person.id}>
          <div>
          {person.firstName} {person.lastName} - {person.nationality}
          </div>

          <div>
          Tulemused:
          {person.results?.map(result => (
              <div key={result.id}>
              Ala: {result.event} | Tulemus: {result.result} | Punktid: {result.points}
              </div>
          ))}
          </div>

          <button onClick={() => deletePerson(person.id!)}>
          Kustuta
          </button>

          <hr />
      </div>
      ))}

      <button disabled={page === 0} onClick={() => setPage(page - 1)}>
        Eelmine
      </button>

      <span>{page + 1} / {totalPages}</span>

      <button disabled={page + 1 === totalPages} onClick={() => setPage(page + 1)}>
        Järgmine
      </button>

    </div>
  );
}

export default PersonsPage;