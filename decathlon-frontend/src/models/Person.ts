export type Result = {
    id?: number;
    event: string;
    result: number;
    points?: number;
}

export type Person = {
    id?: number;
    firstName: string;
    lastName: string;
    birthDate: string;
    nationality: string;
    results: Result[];
}