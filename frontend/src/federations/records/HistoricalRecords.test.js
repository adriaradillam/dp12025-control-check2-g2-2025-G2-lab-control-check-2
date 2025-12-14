import { render, screen } from "../../test-utils";
import HistoricalRecords from "./index";
import { server } from "../../mocks/server";
import { rest } from "msw";
import { waitFor, within } from "@testing-library/react";


describe("HistoricalRecords", () => {

  beforeEach(() => {
    render(<HistoricalRecords id={2} />);
  });

  test("displays the federation title", async () => {
    const heading = await screen.findByRole("heading", { level: 1 });
    expect(heading).toHaveTextContent("European Chess Union - ECU");
  });

  test("renders the events table", async () => {
    const table = await screen.findByRole("table");
    expect(table).toBeInTheDocument();
  });

  test("displays the correct table headers", async () => {
    const table = await screen.findByRole("table");
    const headers = within(table).getAllByRole("columnheader");

    expect(headers[0]).toHaveTextContent("Event name");
    expect(headers[1]).toHaveTextContent("Participants");
  });

  test("includes three event rows plus the header", async () => {
    const table = await screen.findByRole("table");
    const rows = within(table).getAllByRole("row");

    expect(rows.length).toBe(3);
  });

  test("displays the first event with 7 participants", async () => {
    const table = await screen.findByRole("table");
    const rows = within(table).getAllByRole("row");

    const firstRow = rows[1];
    expect(
      within(firstRow).getByText("European Rapid Championship 2025")
    ).toBeInTheDocument();

    const list = within(firstRow).getByRole("list");
    const items = within(list).getAllByRole("listitem");
    expect(items.length).toBe(7);
  });

  test("displays the second event with 1 participant", async () => {
    const table = await screen.findByRole("table");
    const rows = within(table).getAllByRole("row");

    const secondRow = rows[2];
    expect(
      within(secondRow).getByText("European Coaching & Strategy Forum 2025")
    ).toBeInTheDocument();

    const list = within(secondRow).getByRole("list");
    const items = within(list).getAllByRole("listitem");
    expect(items.length).toBe(1);
  });

  

});
